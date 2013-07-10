package com.thebitisland.campamentosdiaper.auxClasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import com.thebitisland.campamentosdiaper.R;

/**
 * Custom ExpandableListAdapter
 * Created by Alvaro S, info extracted from: 
 * http://www.javacodegeeks.com/2013/06/...
 * ...android-expandablelistview-with-custom-adapter-baseexpandablelistadapter.html
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
	private List<Category> groupData;
	private List<List<ItemDetail>> childData;
	private Context context;

	private LayoutInflater inflater;

	public CustomExpandableListAdapter(Context context, List<Category> groupData, List<List<ItemDetail>> childData) {
		this.groupData = groupData;
		this.childData = childData;
		this.context=context;
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
	        v = inflater.inflate(R.layout.item_layout, parent, false);
	    }

	    TextView itemName = (TextView) v.findViewById(R.id.itemName);
	    TextView itemDescr = (TextView) v.findViewById(R.id.itemDescr);

	    ItemDetail det = childData.get(groupPosition).get(childPosition);
	    
	    itemName.setText(det.getName());
	    itemDescr.setText(det.getDescr());

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
		        v = inflater.inflate(R.layout.group_layout, parent, false);
		    }

		    TextView groupName = (TextView) v.findViewById(R.id.groupName);
		    TextView groupDescr = (TextView) v.findViewById(R.id.groupDescr);

		    Category cat = groupData.get(groupPosition);

		    groupName.setText(cat.getName());
		    groupDescr.setText(cat.getDescr());

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

}