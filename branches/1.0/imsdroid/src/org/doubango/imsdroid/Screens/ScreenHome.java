/*
* Copyright (C) 2010 Mamadou Diop.
*
* Contact: Mamadou Diop <diopmamadou(at)doubango.org>
*	
* This file is part of imsdroid Project (http://code.google.com/p/imsdroid)
*
* imsdroid is free software: you can redistribute it and/or modify it under the terms of 
* the GNU General Public License as published by the Free Software Foundation, either version 3 
* of the License, or (at your option) any later version.
*	
* imsdroid is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
* See the GNU General Public License for more details.
*	
* You should have received a copy of the GNU General Public License along 
* with this program; if not, write to the Free Software Foundation, Inc., 
* 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*
*/

package org.doubango.imsdroid.Screens;

import java.util.ArrayList;

import org.doubango.imsdroid.CustomDialog;
import org.doubango.imsdroid.R;
import org.doubango.imsdroid.Services.IScreenService;
import org.doubango.imsdroid.Services.ISipService;
import org.doubango.imsdroid.Services.Impl.ServiceManager;
import org.doubango.imsdroid.events.IRegistrationEventHandler;
import org.doubango.imsdroid.events.RegistrationEventArgs;
import org.doubango.imsdroid.sip.MyAVSession;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ScreenHome extends Screen 
implements IRegistrationEventHandler
{

	/* ===================== Activity ======================== */

	private GridView gridView;
	private ArrayList<ScreenHomeItem> items;
	private static int itemSignInOutPosition = 0;
	private static int itemExitPosition = 1;
	private ScreenHomeAdapter adapter;
	
	private final IScreenService screenService;
	private final ISipService sipService;

	public ScreenHome() {
		super(SCREEN_TYPE.HOME_T, ScreenHome.class.getCanonicalName());

		// Services
		this.screenService = ServiceManager.getScreenService();
		this.sipService = ServiceManager.getSipService();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_home);
		
		this.items = new ArrayList<ScreenHomeItem>();

		// always visible
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.sign_in_48), "Sign In", null));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.exit_48), "Exit/Quit", null));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.options_48), "Options", ScreenOptions.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.about_48), "About", ScreenAbout.class));
		
		// visible only if connected
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.dialer_48), "Dialer", ScreenDialer.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.id_cards_48), "Registrations", ScreenRegistrations.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.lock_edit_48), "Authorizations", ScreenAuthorizations.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.eab2_48), "Address Book", ScreenContacts.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.history_48), "History", ScreenHistory.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.image_gallery_48), "Content Sharing", ScreenFileTransferQueue.class));
		this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
				getResources(), R.drawable.chat_48), "Chat", ScreenChatQueue.class));
		//this.items.add(new ScreenHomeItem(BitmapFactory.decodeResource(
		//		getResources(), R.drawable.avatar_48), "Sessions", ScreenAVQueue.class));
		
		// gridView
		this.adapter = new ScreenHomeAdapter(this.items);
		this.gridView = (GridView) this.findViewById(R.id.screen_home_gridview);
		this.gridView.setAdapter(this.adapter);
		this.gridView_setOnItemClickListener();
		
		// add event handlers
        this.sipService.addRegistrationEventHandler(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//outState.putBoolean("SignInOutEnabled", this.items.get(ScreenHome.itemSignInOutPosition).enabled);
		
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
//		if(this.sipService.isRegistered()){
//			this.adapter.updateItem(ScreenHome.itemSignInOutPosition, BitmapFactory.decodeResource(
//							getResources(), R.drawable.sign_out_48), "Sign Out", null);
//		}
//		else{
//			this.adapter.updateItem(ScreenHome.itemSignInOutPosition, BitmapFactory.decodeResource(
//					getResources(), R.drawable.sign_in_48), "Sign In", null);
//		}
//		this.adapter.setItemEnabled(ScreenHome.itemSignInOutPosition, savedInstanceState.getBoolean("SignInOutEnabled"));
		
		this.adapter.setRegistered(this.sipService.isRegistered());
	}
	
	@Override
	protected void onDestroy() { 
        // remove event handlers
        this.sipService.removeRegistrationEventHandler(this);
        
        super.onDestroy();
	}
	
	/* ===================== IScreen ======================== */
	

	/* ===================== UI Events ======================== */
	private void gridView_setOnItemClickListener() {
		this.gridView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ScreenHomeItem item = ScreenHome.this.items.get(position);
				if(item != null){
					if(position == ScreenHome.itemSignInOutPosition){
						// FIXME
						if(ScreenHome.this.adapter.inprogress){
							ScreenHome.this.sipService.stopStack();
							return;
						}
						
						if(ScreenHome.this.sipService.isRegistered()){
							ScreenHome.this.sipService.unregister();
						}
						else{
							ScreenHome.this.sipService.register();
						}
					}
					else if(position == ScreenHome.itemExitPosition){
						CustomDialog.show(
								ScreenHome.this,
								R.drawable.exit_48,
								null,
								"Are you sure you want to exit?",
								"Yes",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										ServiceManager.getMainActivity().exit();
									}
								}, "No",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.cancel();
									}
								});
					}
					else{					
						ScreenHome.this.screenService.show(item.screenCls, item.screenCls.getCanonicalName());
					}
				}
			}
		});
	}

	/* ===================== Sip Events ========================*/
	public boolean onRegistrationEvent(Object sender, RegistrationEventArgs e) {
		
		Log.i(this.getClass().getName(), "onRegistrationEvent");
		
		switch(e.getType()){
			case REGISTRATION_OK:
			case UNREGISTRATION_OK:
				this.runOnUiThread(new Runnable() {
					public void run() {
						ScreenHome.this.adapter.setRegistered(ScreenHome.this.sipService.isRegistered());
					}});
				break;
				
			case REGISTRATION_INPROGRESS:
			case UNREGISTRATION_INPROGRESS:
				this.runOnUiThread(new Runnable() {
					public void run() {
						ScreenHome.this.adapter.setInprogress(true);
				}});
				break;
				
			case REGISTRATION_NOK:
			case UNREGISTRATION_NOK:
				ScreenHome.this.adapter.setRegistered(ScreenHome.this.sipService.isRegistered());
			default:
				break;
			
		}
		return true;
	}
	
	
	
	/* ===================== Adapter ======================== */

	private class ScreenHomeItem {
		private final Bitmap icon;
		private final String text;
		private final Class<? extends Screen> screenCls;

		private ScreenHomeItem(Bitmap icon, String text, Class<? extends Screen> screenCls) {
			this.icon = icon;
			this.text = text;
			this.screenCls = screenCls;
		}
	}

	private class ScreenHomeAdapter extends BaseAdapter {
		private ArrayList<ScreenHomeItem> items;
		private boolean registered;
		private boolean inprogress;
		
		private ScreenHomeAdapter(ArrayList<ScreenHomeItem> items) {
			this.items = items;
			this.registered = ScreenHome.this.sipService.isRegistered();
		}

		private synchronized void setRegistered(boolean registered){
			this.registered = registered;
			this.inprogress = false;
			this.notifyDataSetChanged();
		}
		
		private synchronized void setInprogress(boolean inprogress){
			this.inprogress = inprogress;
			this.notifyDataSetChanged();
		}

		public int getCount() {
			return this.registered ? this.items.size() : 4/* SignIn/SignOut; Exit/Quit; Options; About */;
			//return this.items.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ScreenHomeItem item;
			final boolean  registered = ScreenHome.this.sipService.isRegistered();

			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.screen_home_item, null);
			}
			if ((item = this.items.get(position)) == null) {
				return view;
			}

			ImageView iv = (ImageView) view.findViewById(R.id.screen_home_item_icon);
			// imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			TextView tv = (TextView) view.findViewById(R.id.screen_home_item_text);
			
			if(position == ScreenHome.itemSignInOutPosition){
				if(this.inprogress){
					tv.setText("Cancel");
					iv.setImageDrawable(getResources().getDrawable(R.drawable.sign_inprogress_48));
				}
				else{
					if(registered){
						tv.setText("Sign Out");
						iv.setImageDrawable(getResources().getDrawable(R.drawable.sign_out_48));
					}
					else{
						tv.setText("Sign In");
						iv.setImageDrawable(getResources().getDrawable(R.drawable.sign_in_48));
					}
				}
			}
			else{				
				tv.setText(item.text);
				iv.setImageBitmap(item.icon);
			}

			return view;
		}
	}
}
