package com.akbarsha03.agive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlowerView flowerView = (FlowerView) findViewById(R.id.anID);

        List<LeafValue> leafValues = new ArrayList<>();

        /*
         * More than 4 leaves supported
         */
        leafValues.add(new LeafValue("#FFBA00", 10));
        leafValues.add(new LeafValue("#FF4B00", 30));
        leafValues.add(new LeafValue("#00DA57", 70));
        leafValues.add(new LeafValue("#3E63FF", 50));
//        leafValues.add(new LeafValue("#00f00f", 50));
//        leafValues.add(new LeafValue("#0000ff", 30));

        /**
         * Build the flower with following parameters
         *
         * @param leafRadius        pass minimum 400 to get a better view
         * @param centerRadius      pass minimum 100
         * @param centerTextSize    pass minimum 60f
         * @param overAllPercentage pass between 0 and 100
         * @param leafValues        Pass @{@link LeafValue}
         */
        flowerView.setUpLeaves(500, 100, 60f, 100, leafValues);

//        final MyView16 view = (MyView16) findViewById(R.id.anID);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Random rand = new Random();
//                int randomNum = 0 + rand.nextInt((100 - 0) + 1);
//                view.setProgress(randomNum);
//                view.postInvalidate();
//            }
//        });

//        CustomView view = (CustomView) findViewById(R.id.anID);
//        view.setImage(R.drawable.ic_rocket);
//        view.setImage(R.drawable.ic_launcher);
    }
}
