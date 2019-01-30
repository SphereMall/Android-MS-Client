package com.spheremall.core.resources;

import android.content.Context;
import android.content.SharedPreferences;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.utils.StaticPreferencesManager;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.logging.HttpLoggingInterceptor;

import static org.mockito.ArgumentMatchers.anyString;

public class SetUpResourceTest {

    protected SMClient client;
    protected SharedPreferences preferences;
    protected SharedPreferences.Editor editor;

    @Before
    public void setUp() throws SphereMallException, IOException {

        preferences = Mockito.mock(SharedPreferences.class);
        editor = Mockito.mock(SharedPreferences.Editor.class);
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getSharedPreferences("MS_ANDROID_CLIENT", 0)).thenReturn(preferences);
        String mockToken = "Ozf/rj21pjNeQyRCJTmegJyFEYfLkcdyz7HlLDqzoUAz+I5EZEbhF09RcE+NlnmXq2xRJaSarY/UjshvV4Iv+w94fFXjijw9ZFj7sRDj4L3GamXxFEgJe9CLRJPvOc8kAYTRwe2QmwKVNHs9B7VNJ74+WFQ+goSaOufCxBIedM/Psut/W3Bnhm33aXvbNKxFVSmXJd+A61IbJH5WmXnKSwHh20Uc0LnEYvm/xe+KVOjKcw09rItmKW8bY/t//Msbl1zYa7buh6E=.6pLvPAzeXzPwlNaetSXEiSnopNslgd7fU5DM0fQjT5db7/YH4Bw/R+MiidXcBBDQw2X5Z8onQYJtRxXtw/utK1+kuvVOxDhhPYm87k91IBqR9BL1387HIxfhholoGmHmW8CZbOzVTC+CL+yB8ViF9G9dkiAWAoiTFz0G2hWyRFg=.3523ed73b7025b5a7fd247389aaef048";
        Mockito.when(preferences.getString("TOKEN", "")).thenReturn(mockToken);
        Mockito.when(preferences.edit()).thenReturn(editor);
        Mockito.when(editor.putString(anyString(), anyString())).thenReturn(editor);

        client = SMClient.initialize(
                context,
                ApiConstants.API_GATEWAY_URL,
                ApiConstants.API_CLIENT_ID,
                ApiConstants.API_SECRET_KEY,
                ApiConstants.API_VERSION);

        client.setPreferencesManager(new StaticPreferencesManager());
        client.setDebug(true);
        client.setLoggingLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @After
    public void termDown() {
        client = null;
    }
}