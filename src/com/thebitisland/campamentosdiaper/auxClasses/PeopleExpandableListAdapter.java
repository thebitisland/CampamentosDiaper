package com.thebitisland.campamentosdiaper.auxClasses;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.thebitisland.campamentosdiaper.R;

/**
 * Custom ExpandableListAdapter
 * Created by Alvaro S, info extracted from: 
 * http://www.javacodegeeks.com/2013/06/...
 * ...android-expandablelistview-with-custom-adapter-baseexpandablelistadapter.html
 */

public class PeopleExpandableListAdapter extends BaseExpandableListAdapter {
	private List<Person> groupData;
	private List<List<PersonOptions>> childData;
	private Context context;
	DBManager database;


	private LayoutInflater inflater;

	public PeopleExpandableListAdapter(Context context, List<Person> groupData, List<List<PersonOptions>> childData) {
		this.groupData = groupData;
		this.childData = childData;
		this.context=context;
		database = new DBManager(this.context);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return childData.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View v = convertView;

	    if (v == null) {
	        LayoutInflater inflater = (LayoutInflater)context.getSystemService
	                  (Context.LAYOUT_INFLATER_SERVICE);
	        v = inflater.inflate(R.layout.contact_expanded, parent, false);
	    }

	    Button callBtn = (Button) v.findViewById(R.id.call_button);
	    Button addBtn = (Button) v.findViewById(R.id.addcontact_button);
	    Button smsBtn = (Button) v.findViewById(R.id.sms_button);
	    
	    final PersonOptions det = childData.get(groupPosition).get(childPosition);
	    
	    callBtn.setOnClickListener(new OnClickListener() {
	    	public void onClick(android.view.View arg0) {
	    		
	    		//Get the user's phone number
	    		database.open();
	    		String phoneNumber = database.getPhoneNumber((int)det.getID());
	    		database.close();
	    		
	    		//Launch intent
	    		Intent callIntent = new Intent(Intent.ACTION_DIAL);
	    		callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		callIntent.setData(Uri.parse("tel:" + phoneNumber));
	    		context.startActivity(callIntent);
	    	}
	    });
	    addBtn.setOnClickListener(new OnClickListener() {
			public void onClick(android.view.View arg0) {
				
				//Get the user info using its ID
				database.open();
				String[] user = database.getUserInfo((int)det.getID());
				Log.d("callBtn", "User with id="+det.getID()+": "+user[0]+" "+user[1]+" "+user[2]+" "+user[3]);
				database.close();
				
				//Fill the Contact Provider with the basic user info
				addContact(user);
				//Add the new contact to the phone's list
			}
    	});
	    
	    smsBtn.setOnClickListener(new OnClickListener() {
	    	public void onClick(android.view.View arg0) {
	    		//Get the user's phone number
	    		database.open();
	    		String phoneNumber = database.getPhoneNumber((int)det.getID());
	    		database.close();
	    		
	    		//Launch intent
	    		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
	    		smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		smsIntent.setData(Uri.parse("sms:" + phoneNumber));
	    		context.startActivity(smsIntent);
	    	}
	    });
	    callBtn = det.getCallButton();
	    addBtn = det.getAddContactButton();
	    smsBtn = det.getSMSButton();

	    return v;
	}

	/**
	 * Instantiates a new View for a child.
	 * 
	 * @param parent
	 *            The eventual parent of this new View.
	 * @return A new child View
	 */
	public View newChildView(boolean isLastChild, ViewGroup parent){return null;}

	/**
	 * @param childPosition
	 *            Position of the child in the childData list
	 * @param groupPosition
	 *            Position of the child's group in the groupData list
	 * @param v
	 *            The view to bind data to
	 * @param parent
	 *            The eventual parent of v.
	 */
	public void bindChildView(int childPosition, int groupPosition,
			boolean isLastChild, View v, ViewGroup parent){}

	public int getChildrenCount(int groupPosition) {
		return childData.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return groupData.get(groupPosition);
	}

	public int getGroupCount() {
		return groupData.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {


		    View v = convertView;

		    if (v == null) {
		        LayoutInflater inflater = (LayoutInflater)context.getSystemService
		                  (Context.LAYOUT_INFLATER_SERVICE);
		        v = inflater.inflate(R.layout.contact_item, parent, false);
		    }

		    ImageView photo = (ImageView) v.findViewById(R.id.contact_image);
		    TextView fullname = (TextView) v.findViewById(R.id.contact_name);
		    TextView telephone = (TextView) v.findViewById(R.id.phone_number);
		    TextView occupation = (TextView) v.findViewById(R.id.occupation);
		    Person cat = groupData.get(groupPosition);

		    photo.setImageResource(cat.getPhoto());
		    fullname.setText(cat.getFullName());
		    telephone.setText(cat.getTelephone());
		    occupation.setText(cat.getOccupation());

		    return v;

	}

	/**
	 * Instantiates a new View for a group.
	 * 
	 * @param isExpanded
	 *            Whether the group is currently expanded.
	 * @param parent
	 *            The eventual parent of this new View.
	 * @return A new group View
	 */
	public View newGroupView(boolean isExpanded, ViewGroup parent){return null;}

	/**
	 * @param groupPosition
	 *            Position of the group in the groupData list
	 * @param isExpanded
	 *            Whether the group is currently expanded.
	 * @param v
	 *            The view to bind data to
	 * @param parent
	 *            The eventual parent of v.
	 */
	public void bindGroupView(int groupPosition, boolean isExpanded,
			View v, ViewGroup parent){}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}

	public LayoutInflater getInflater() {
		return inflater;
	}
	
	/*http://stackoverflow.com/questions/6265420/how-to-add-contact-detial-through-code-in-contact-list-of-android-phone*/
	public void addContact(String[] contact) {
		
		/* Retrieve the new contact's info */
		String contactName = contact[0];
		//String contactBirthdate = contact[1];
		String contactEmail = contact[1];
		String contactPhone = contact[2];
		
		/* Create the Android Contact base structure */
		ArrayList<ContentProviderOperation> data = new ArrayList<ContentProviderOperation>();
        data.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        
        /* Add the new contact name to Contact structure */
        data.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName).build());
        
        /* Add the new contact's birthdate to Contact structure */
        /*data.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Event.TYPE, 
                        ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY)
                .withValue(ContactsContract.CommonDataKinds.Event.START_DATE, contactBirthdate)
                .build());
        */
        
        /* Add the new contact's mobile number to Contact structure */
        data.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contactPhone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());
        
        /* Add the new contact's email address to the Contact structure */
        data.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, contactEmail)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                .build());
        
     // Asking the Contact provider to create a new contact                  
        try 
        {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, data);
        } 
        catch (Exception e) 
        {               
            e.printStackTrace();
            Toast.makeText(context, "Could not add new contact", Toast.LENGTH_SHORT).show();
        }
        
        Toast.makeText(context, "New contact added", Toast.LENGTH_SHORT).show();
	}

}