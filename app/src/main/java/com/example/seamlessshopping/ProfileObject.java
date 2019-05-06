package com.example.seamlessshopping;

import android.text.Editable;

public class ProfileObject{
        private String bdayP;
        private String genderP;
        private String mobileP;
        private String personalemailP;
    private String fname;

    private String lname;




    public String getBdayP() {
            return bdayP;
        }


    public ProfileObject(String fname, String lname,String bdayP, String mobileP, String personalemailP) {
        this.bdayP = bdayP;
        this.fname = fname;
        this.lname=lname;
        this.mobileP = mobileP;
        this.personalemailP = personalemailP;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setBdayP(String bdayP) {
            this.bdayP = bdayP;
        }

        public String getGenderP() {
            return genderP;
        }

        public void setGenderP(String genderP) {
            this.genderP = genderP;
        }

        public String getMobileP() {
            return mobileP;
        }

        public void setMobileP(String mobileP) {
            this.mobileP = mobileP;
        }

        public String getPersonalemailP() {
            return personalemailP;
        }

        public void setPersonalemailP(String personalemailP) {
            this.personalemailP = personalemailP;
        }
}
