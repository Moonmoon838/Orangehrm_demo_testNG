package utils;

import config.EmployeeModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveEmployeeInfo(EmployeeModel model) throws IOException, ParseException {
        String file = "./src/test/resources/employees.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(file));
        JSONObject empObj = new JSONObject();
        empObj.put("firstName",model.getFirstname());
        empObj.put("lastName",model.getLastname());
        empObj.put("username",model.getUsername());
        empObj.put("password",model.getPassword());

        jsonArray.add(empObj);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static JSONArray readJSONList(String fileName) throws IOException, ParseException {
        JSONParser jsonParser =new JSONParser();
        JSONArray empArray = (JSONArray) jsonParser.parse(new FileReader(fileName));

        return empArray;
    }

//    public static JSONArray readEmployeeInfo() throws IOException, ParseException {
//        String file ="./src/test/resources/employees.json";
//        JSONParser jsonParser =new JSONParser();
//        JSONArray empArray = (JSONArray) jsonParser.parse(new FileReader(file));
//
//        return empArray;
//    }
}
