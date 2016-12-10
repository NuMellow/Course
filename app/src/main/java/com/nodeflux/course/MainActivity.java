package com.nodeflux.course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView courseList;
    private DatabaseReference courseDb;

    private String countryCluster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryCluster = "Namibia-Agriculture"; //gets country from country selection and appends it with Cluster selection
        courseDb = FirebaseDatabase.getInstance().getReference().child("Course");

        courseList = (RecyclerView) findViewById(R.id.course_list);
        courseList.setHasFixedSize(true);
        courseList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Course, CourseViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(
                Course.class,
                R.layout.course_row,
                CourseViewHolder.class,
                courseDb.orderByChild("Country").equalTo(countryCluster)
        ) {
            @Override
            protected void populateViewHolder(CourseViewHolder viewHolder, Course model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setUni(model.getUniversity());
            }
        };

        courseList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder
    {
        View myView;

        public CourseViewHolder(View itemView)
        {
            super(itemView);

            myView = itemView;
        }

        public void setName(String name)
        {
            TextView courseName = (TextView) myView.findViewById(R.id.textCourseName);
            courseName.setText(name);
        }

        public void setUni(String uName)
        {
            TextView uniName  = (TextView) myView.findViewById(R.id.textUniName);
            uniName.setText(uName);
        }

    }

}
