package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class SearchActivity extends Activity{
	
	ArrayList<String> searchedArtList;
	//ArrayList<ArtWork> searchedArtWork;
	ListView list;
	Context c = this;
	ItemsAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.search);  
	    list = (ListView) findViewById(R.id.searchList);
	    
	    final ImageButton searchB = (ImageButton) findViewById(R.id.searchNOW);
		searchB.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final EditText searchText = (EditText) findViewById(R.id.searchTextBox);
				String userText = searchText.getText().toString();
				if(!userText.isEmpty()){
					try{
						ArrayList<ArtWork> searchArtWork = ParseArtWorkXML.artWorkRequestIdentifier(userText);
						/*searchedArtList = new ArrayList<String>();
					
						for(int i = 0; i < searchArtWork.size(); i++){
							
							if(searchArtWork.get(i).description.length() > 58){
								searchedArtList.add(searchArtWork.get(i).artTitle + "\n" + searchArtWork.get(i).description.substring(0, 58) + "...");
							}else{
								searchedArtList.add(searchArtWork.get(i).artTitle + "\n" + searchArtWork.get(i).description);
							}
						}	*/
						
						adapter = new ItemsAdapter(c, R.layout.search_list, searchArtWork);
						
						//String[] searchInfo = new String[searchedArtList.size()];
						//searchInfo = searchedArtList.toArray(searchInfo);
						//list.setAdapter(new ArrayAdapter<String>(c, R.layout.artdetail_list, searchInfo));
						list.setAdapter(adapter);
					}catch(Exception e){
						//Toast.makeText(c, "Error searching", Toast.LENGTH_LONG);
					}
				}
			}
		});
	}
}
