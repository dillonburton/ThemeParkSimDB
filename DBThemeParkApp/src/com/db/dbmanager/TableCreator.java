package com.db.dbmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableCreator extends DBHandler{

	// List of column names to take from database table
	private String[] columnNames;

	// ID for the table in layout file
	private int tableId;

	public TableCreator(Context context, int tableId, String ... columnNames) {
		super(context);
		this.columnNames = columnNames;
		this.tableId = tableId;
	}

	/**
	 * See super classes (DB.java) abstract declaration method for updateUI
	 */
	@Override
	public void updateUI(String result) {

		try {

			// Store the string result from the DB table into a JSON array
			JSONArray jsonArray = new JSONArray(result);

			// Reference the table layout in the layout file
			TableLayout currentTableLayout = (TableLayout)((Activity)context).
					findViewById(tableId);

			// If the row should be a solid color (Every other row is filled_
			boolean fillColor = false;

			// Column header row
			TableRow row = new TableRow(context);

			/* Create a text view for the column headers from the column names */
			for(String s : columnNames){
				row.addView(createTextView(s, fillColor));
			}

			/* Fixes bug when switching tables too fast by making sure that the table 
			 * layout isn't null */
			if(currentTableLayout != null){
				currentTableLayout.addView(row);
			}else {
				System.out.println("Requesting too quickly; try again in a few seconds");
				return;
			}

			/* Create each row for the table */
			for (int rowIndex = 0; rowIndex < jsonArray.length(); rowIndex++) {

				// Every other row is filled
				fillColor = !fillColor;

				/* Create the table row and set parameters to stretch */
				TableRow tr = new TableRow(context);
				TableLayout.LayoutParams p = new TableLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
				tr.setLayoutParams(p);

				// Create a JSON object to parse the text at the array index and create an object
				JSONObject jsonObject = jsonArray.getJSONObject(rowIndex);

				/* Create a text view row from JSON Object */
				for(String s : columnNames){
					tr.addView(createTextView(jsonObject.getString(s), fillColor));
				}

				/* Fixes bug when switching tables too fast by making sure that the table 
				 * layout isn't null */
				if(currentTableLayout != null){
					currentTableLayout.addView(tr);
				}else{
					System.out.println("Requesting too quickly; try again in a few seconds");
					break;
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a text view to add as a column in a table
	 * @param value is the String value for the text view
	 * @param fill is if the entry should be filled
	 * @return the generated text view
	 */
	public TextView createTextView(String value, boolean fill) throws JSONException{

		/* Create and set up properties of text view */
		TextView textView = new TextView(context);
		textView.setPadding(10, 0, 0, 0);
		textView.setTextSize(24);
		textView.setTextColor(Color.parseColor("#585858"));

		/* Set layout parameters to stretch */
		textView.setLayoutParams(new TableRow.LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 1.0f));
		textView.setText(value);

		/* Fill color if needs to be filled */
		if(fill){
			textView.setBackgroundColor(Color.parseColor("#C6CDD1"));
		}
		return textView;
	}
}
