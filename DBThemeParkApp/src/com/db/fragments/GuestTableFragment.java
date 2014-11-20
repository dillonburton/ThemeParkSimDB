package com.db.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.db.dbmanager.TableCreator;
import com.example.dbthemeparkapp.R;

public class GuestTableFragment extends Fragment {
	
	public GuestTableFragment(){}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_test_2, container, false);
		
		TableCreator dbh = new TableCreator(getActivity(), R.id.table2, "id", "name", "sex", "arrival_date");
		dbh.execute("test.php");
		
		return rootView;
	}
}