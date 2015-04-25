package com.rubio.ecocugat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rubio.ecocugat.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	
	private ImageView basura;
	String result="";
	
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem.id.equalsIgnoreCase("1")) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText(mItem.content);
			
			
			BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.camionbasura);			
			rootView.setBackgroundDrawable(bg); 
			
			InputStream is = null;
			try {
				is = rootView.getContext().getAssets().open("posiciones.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String date = null,value;
			Integer visible = 0;
			
			 BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			    try {
			        String line;
			        while ((line = reader.readLine()) != null) {
			             String[] RowData = line.split(",");
			             if (RowData.length>1)
			             {
				             date = RowData[1];
				             value = RowData[0];
 				 			 if(date.startsWith("52687")&&(visible==0))
 				 			 {
								((ImageView) rootView.findViewById(R.id.semaforoverde)).setVisibility(View.VISIBLE);
								((ImageView) rootView.findViewById(R.id.semafororojo)).setVisibility(View.INVISIBLE);
								visible=1; 
 				 			 }
						     else if(visible==0) 
						     {
								((ImageView) rootView.findViewById(R.id.semaforoverde)).setVisibility(View.INVISIBLE);
 				 			 	((ImageView) rootView.findViewById(R.id.semafororojo)).setVisibility(View.VISIBLE);
						     }

			             }
			            // do something with "data" and "value"
			        }
			    }
			    catch (IOException ex) {
			        // handle exception
			    }
			    finally {
			        try {
			            is.close();
			        }
			        catch (IOException e) {
			            // handle exception
			        }
			    }						
		}
		else if (mItem.id.equalsIgnoreCase("4")) {
			((TextView) rootView.findViewById(R.id.item_detail))
			.setText(mItem.content);	
	
			InputStream is = null;
			try {
				is = rootView.getContext().getAssets().open("elementos.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String name = null,pos = null, peso=null;
			Integer visible = 0;
			
			 BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			    try {
			        String line;
			        while ((line = reader.readLine()) != null) {
			             String[] RowData = line.split(";");
			             if (RowData.length>1)
			             {
				             name = RowData[1];
				             pos = RowData[8];
				             peso = RowData[0]; 
					 			 if(pos.startsWith("2,068")&&(Integer.parseInt(peso)<=252)) 
					 			 {
					 				 result=result+name+"\n";
					 				 visible=1; 
					 			 }
						     else if(visible==0) 
						     {

						     }
		
			             }
			 			((TextView) rootView.findViewById(R.id.item_detail2))
						.setText(result);
			            // do something with "data" and "value"
			             
//						    AlertDialog.Builder b = new AlertDialog.Builder(rootView.getContext());
//						    b.setTitle("Contenidors a prop?"); 
//						    b.setCancelable(false);
//						    final EditText input = new EditText(rootView.getContext());
//						    input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//						    b.setView(input);
//						    b.setPositiveButton("OK", new DialogInterface.OnClickListener()
//						    {
//						        public void onClick(DialogInterface dialog, int whichButton)
//						        {
//						           // SHOULD NOW WORK
//						        	input.setText(result);						        	
//						        }
//						    });
//						    //b.setNegativeButton("CANCEL", null);				    	
//						    final AlertDialog alertDialoge = b.create();
//						    alertDialoge.show();				    

			             
			             
			             
			        }
			    }
			    catch (IOException ex) {
			        // handle exception
			    }
			    finally {
			        try {
			            is.close();
			        }
			        catch (IOException e) {
			            // handle exception
			        }
			    }						
			
		}

		return rootView;
	}
}

