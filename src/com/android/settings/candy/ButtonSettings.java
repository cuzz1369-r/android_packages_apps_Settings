/*
* Copyright (C) 2015 CandyRoms
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.android.settings.candy;

import android.os.Bundle;
import android.preference.SwitchPreference;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsLogger;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.content.ContentResolver;
import android.content.res.Resources;
import java.util.prefs.PreferenceChangeListener;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

public class ButtonSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {
	
	private static final String TAG = ButtonSettings.class.getSimpleName();
	
	public static final String VOLBTN_MUSIC_CONTROLS = "volbtn_music_controls";
	
	private SwitchPreference mVolumeRockerMusicControl;
	
	 @Override
    protected int getMetricsCategory() {
        return MetricsLogger.DEVELOPMENT;
    }
	
	 @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         addPreferencesFromResource(R.xml.candy_settings_buttons);
 
         final ContentResolver resolver = getContentResolver();
         final PreferenceScreen prefScreen = getPreferenceScreen();
         final Resources res = getResources();
    
        mVolumeRockerMusicControl = (SwitchPreference) findPreference(VOLBTN_MUSIC_CONTROLS);
        int volumeRockerMusicControl = Settings.System.getInt(getContentResolver(),
                VOLBTN_MUSIC_CONTROLS, 0);
        mVolumeRockerMusicControl.setChecked(volumeRockerMusicControl != 0);
     }
  
     @Override
     public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
         return super.onPreferenceTreeClick(preferenceScreen, preference);
     }

    @Override
     public boolean onPreferenceChange(Preference preference, Object newValue) {
		 if (preference == mVolumeRockerMusicControl) {
            boolean value = (Boolean) newValue;
            Settings.System.putInt(getContentResolver(), VOLBTN_MUSIC_CONTROLS,
                    value ? 1 : 0);
            return true;
         }
       return false;
      }  
  }
