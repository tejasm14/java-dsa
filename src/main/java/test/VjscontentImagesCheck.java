package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
public class VjscontentImagesCheck {

    public static void main(String[] args) {
        //String filePath = "E:\\PDP-URLs.xls"; // Change this to your file path
        String filePath = "C:\\Users\\tejas.mohite\\Downloads\\a-plus-content.xls"; // Change this to your file path
        JsonObject resp = new JsonObject();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new HSSFWorkbook(fis)) {
            int colIndex = 4;
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            JsonArray imgArr = new JsonArray();
            int i = 1;
            for (Row row : sheet) {
                if(i==1) {
                    i++;

                    continue;
                } else if (i > 20001) {
                    break;
                }
                Cell skuCell = row.getCell(0);
                Cell cell = row.getCell(colIndex);
                if (cell != null && !cell.toString().isEmpty()) {
                    imgArr.add(cell.toString());
                    String url = cell.toString();
                    JsonObject respObj = callImgUrl(url);
                    respObj.addProperty("url", url);
                    String sku = skuCell.toString();
                    respObj.addProperty("sku", sku);
                    System.out.println(i +" --> "+sku + "-->" + respObj);
                    resp.add(url, respObj);
                    i++;
                } else {
                    i++;
                }
            }
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject callImgUrl(String imgUrl) {
        // TODO Auto-generated method stub
        JsonObject respObj = new JsonObject();
        imgUrl = "https://shop.vijaysales.com/"+imgUrl;
        //imgUrl = imgUrl.replace("stage", "beta");
        try {
            JsonObject resp = callApi("GET", new JsonObject(), imgUrl, new JsonObject());
            if(resp.get("statusCode").getAsInt() == 200) {
                respObj.addProperty("status", 200);
            } else {
                respObj.addProperty("status", resp.get("statusCode").getAsInt());
                respObj.addProperty("response", resp.get("response").getAsString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
            respObj.addProperty("status", "");
            respObj.addProperty("error", e.getMessage());
        }
        return respObj;
    }

    private static JsonObject callApi(String httpMethod, JsonObject requestBody, String url, JsonObject requestHeader) throws IOException {
        HttpRequestBase httpRequestBase = getHttpRequest(httpMethod, requestBody, url, requestHeader);
        JsonObject jsonObject = new JsonObject();
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpRequestBase)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());
            jsonObject.addProperty("statusCode", statusCode);
            jsonObject.addProperty("response",result);
        }
        return jsonObject;
    }

    private static HttpRequestBase getHttpRequest(String httpMethod, JsonObject requestBody, String url, JsonObject requestHeader) throws ServerException, UnsupportedEncodingException {
        HttpRequestBase httpRequestBase;
        if (httpMethod.equalsIgnoreCase("post")) {
            httpRequestBase = getHttpPost(url, requestBody, requestHeader);
        }
        else {
            httpRequestBase = getHttpGet(url, requestBody, requestHeader);
        }
        return httpRequestBase;
    }
    private static HttpRequestBase getHttpPost(String url,JsonObject requestBody, JsonObject requestHeader) throws UnsupportedEncodingException {
        String result ="";
        HttpPost httpPost = new HttpPost(url);
        requestHeader.entrySet().forEach(entry -> {
            httpPost.setHeader(entry.getKey(), entry.getValue().getAsString());
        });
        StringEntity stringEntity = new StringEntity(requestBody.toString());
        httpPost.setEntity(stringEntity);
        return httpPost;
    }

    private static HttpRequestBase getHttpGet(String url,JsonObject requestBody, JsonObject requestHeader) throws UnsupportedEncodingException {
        String result ="";
        HttpGet httpPost = new HttpGet(url);
        requestHeader.entrySet().forEach(entry -> {
            httpPost.setHeader(entry.getKey(), entry.getValue().getAsString());
        });
        return httpPost;
    }
}
