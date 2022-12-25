package com.alwi.navigationbar.Model;

import java.io.Serializable;

public class ModelUser implements Serializable {

    public String NamaUser;
    public String Username;

    public ModelUser(String namaUser, String username) {
        this.NamaUser = namaUser;
        this.Username = username;
    }

    public String getNamaUser() {
        return NamaUser;
    }

    public String getUsername() {
        return Username;
    }
}
