package com.example.clubevents;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class FireStoreContents {
    private static final String TAG = "FireStoreContents";
    final ArrayList<String> Title,Content,Link,Img_src;

    public FireStoreContents(){
        Title=new ArrayList<>();
        Content=new ArrayList<>();
        Link=new ArrayList<>();
        Img_src=new ArrayList<>();
    }

    public void getDocument(String collection_name, final Context context) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(collection_name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> single_post = document.getData();
                                Title.add(single_post.get("Time").toString());
                                Content.add(single_post.get("Content").toString()+'\n');
                                Link.add(single_post.get("Link").toString());

                                if(single_post.get("Image Src")!=null){
                                    Img_src.add(single_post.get("Image Src").toString());
                                }
                                else{
                                    Img_src.add("NA");
                                }
                            }
                            Collections.reverse(Title);
                            Collections.reverse(Content);
                            Collections.reverse(Link);
                            Collections.reverse(Img_src);
                            CustomListAdapter adapter = new CustomListAdapter((Activity)context, Title, Content, Link, Img_src);
                            ListView listView = ((Activity)context).findViewById(R.id.cards_list);
                            listView.setAdapter(adapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
