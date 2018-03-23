package com.spheremall.core.resources;

import android.content.Context;
import android.content.SharedPreferences;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.utils.StaticPreferencesManager;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;

public class SetUpResourceTest {

    protected SMClient client;
    protected SharedPreferences preferences;
    protected SharedPreferences.Editor editor;
    private String mockToken = "ozBzTRQvrudyGpHJ34LbcT/zm2a4jbuoHpgDLMNCAKkEeAJyqipBRNOhBLcc/cnnUQpcms+Io11s0vXfU6uBtIEl5AejiAAIw6mlJcE2pB/tRnraP9LesmILvuq0+atLcryjVjEcSOvygTO5n1Q4ik/DRQgX3brnlT8ZxZuU2E9mGzt8AgqZZw4UfHnhkDN1MN+cSki7PFJrm1JyS/Id24NCs8UZNOrZ16l+vgJXtn96+XqRudx1GaxfEXj8g0EqEIBCJw==.8Bk12U3J4SRIhY859ORcJ+zrWwgY3gzFWX4c5D+MeX6av+UWg/BCeGTAikyzOGleqHxCasEKSNvC08DLIMpAMhjVun2bsR/NoykrIuw2oFfNYO2/DT92SuRtHz0pBEIZpYZ4oy5IYwoibh6dx0YBhs8qz+Qq5w==.3d0eec579e9f5d8cf34b248b23817d37";

    @Before
    public void setUp() throws EntityNotFoundException, ServiceException, IOException {
        preferences = Mockito.mock(SharedPreferences.class);
        editor = Mockito.mock(SharedPreferences.Editor.class);
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getSharedPreferences("MS_ANDROID_CLIENT", 0)).thenReturn(preferences);
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

//        User user = client.auth().login("o.hrybuk@spheremall.com", "123");
    }

    @After
    public void termDown() throws EntityNotFoundException, ServiceException, IOException {
        client = null;
    }
}