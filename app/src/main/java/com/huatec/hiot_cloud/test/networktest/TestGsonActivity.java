package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatec.hiot_cloud.R;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TestGsonActivity extends AppCompatActivity {

    private static final String TAG = "TestGsonActivity";
    private Gson gson = new Gson();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gson);

        //Json转对象
        Button btnFormJson = findViewById(R.id.btn_form_json);
//        btnFormJson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Student student = gson.fromJson("{\n" +
//                        "\t\"age\": 20,\n" +
//                        "\t\"married\": false,\n" +
//                        "\t\"name\": \"张三\"\n" +
//                        "}", Student.class);
//                if(student!=null){
//                    String str = String.format("姓名: %s,年龄：%d,  婚配: %b",
//                            student.getName(), student.getAge(), student.isMarried());
//                    Toast.makeText(TestGsonActivity.this,str,Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //对象转Json
        Button btnToJson = findViewById(R.id.btn_to_json);
        btnToJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student2 = new Student();
                student2.setName("李四");
//                student2.setAge(18);
//                student2.setMarried(true);
                student2.setHeight(175);
                student2.setId(101);
                String json = gson.toJson(student2);
                Log.d(TAG, "onClick: " + json);
            }
        });

        //Json转列表
        Button btnJsonList = findViewById(R.id.btn_json_list);
//        btnJsonList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String json = "[\n" +
//                        "\t{\n" +
//                        "\t\t\"age\": 20,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"张三\"\n" +
//                        "\t},\n" +
//                        "\t{\n" +
//                        "\t\t\"age\": 21,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"李四\"\n" +
//                        "\t},\n" +
//                        "\t{\n" +
//                        "\t\t\"age\": 22,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"王五\"\n" +
//                        "\t}\n" +
//                        "]";
//                Type type = new TypeToken<List<Student>>(){}.getType();
//                List<Student> studentList = gson.fromJson(json, type);
//                if (studentList != null || !studentList.isEmpty()){
//                    String str = "";
//                    for (Student student: studentList) {
//                        str  = str + String.format("姓名: %s,年龄：%d,  婚配: %b",
//                                student.getName(), student.getAge(), student.isMarried());
//                    }
//                    Toast.makeText(TestGsonActivity.this,str,Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


        //Json转Map
        Button btnJsonMap = findViewById(R.id.btn_json_map);
//        btnJsonMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String json = "{\n" +
//                        "\t\"1\":{\n" +
//                        "\t\t\"age\": 20,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"张三\"\n" +
//                        "\t},\n" +
//                        "\t\"2\":{\n" +
//                        "\t\t\"age\": 21,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"李四\"\n" +
//                        "\t},\n" +
//                        "\t\"3\":{\n" +
//                        "\t\t\"age\": 22,\n" +
//                        "\t\t\"married\": false,\n" +
//                        "\t\t\"name\": \"王五\"\n" +
//                        "\t}\n" +
//                        "}";
//                Type type = new TypeToken<Map<String,Student>>(){}.getType();
//                Map<String, Student> map = gson.fromJson(json, type);
//                String str = " ";
//                if (map != null){
//                    for(String key: map.keySet()){
//                        str  = str + String.format("序号：%s, 姓名: %s,年龄：%d,  婚配: %b",key
//                                ,map.get(key).getName(),  map.get(key).getAge(),  map.get(key).isMarried());
//
//                    Toast.makeText(TestGsonActivity.this,str,Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//        });

        //Json转嵌套对象
        Button btnJsonNest = findViewById(R.id.btn_json_nest);
        btnJsonNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = "{\n" +
                        "\t\"data\":\n" +
                        "\t{\n" +
                        "\t\t\"id\": 101,\n" +
                        "\t\t\"graduated\": false,\n" +
                        "\t\t\"height\":180,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t\"status\":1,\n" +
                        "\t\"msg\":\"\"\n" +
                        "}";
                Type type = new TypeToken<ResultBase<Student>>() {
                }.getType();
                ResultBase<Student> resultBase = gson.fromJson(json, type);
                String str = "";
                if (resultBase != null) {
                    str = str + String.format(" id: %d, 姓名: %s,  身高：%d,  是否毕业: %b"
                            , resultBase.data.getId(), resultBase.data.getName(), resultBase.data.getHeight(), resultBase.data.isGraduated());
                    Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
