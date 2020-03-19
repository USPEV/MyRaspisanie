package ru.maxfad.myraspisanie;

/**
 * Created by maxfad on 01.12.2017.
 */

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_main11 extends ListFragment {
    String classID;
    SimpleCursorAdapter userAdapter;
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    ListView mList;


    public fragment_main11() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootview = inflater.inflate(R.layout.fragment_main11, container, false);
        mList= (ListView)  rootview.findViewById(android.R.id.list);
        return rootview;
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        classID = bundle.getString("classID");
        sqlHelper = new DatabaseHelper(getActivity());
        sqlHelper.create_db();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper.TABLE + " where week LIKE" + "'%|"+classID+"|%'" +" and day=1", null);
            String[] headers = new String[]{DatabaseHelper.COLUMN_SUBJECT};
            userAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item, userCursor, headers, new int[]{R.id.uroki},0);
            mList.setAdapter(userAdapter);

        }
        catch (SQLException ex){}
    }



}


