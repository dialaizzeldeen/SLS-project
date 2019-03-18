package com.example.seamlessshopping;

public class ProfileObject{
        private String bdayP;
        private String genderP;
        private String mobileP;
        private String personalemailP;

        public ProfileObject(String usernameP, String bdayP, String genderP, String mobileP, String personalemailP) {


            this.bdayP = bdayP;
            this.genderP = genderP;
            this.mobileP = mobileP;
            this.personalemailP = personalemailP;
        }



        public String getBdayP() {
            return bdayP;
        }

    public ProfileObject( String genderP,String bdayP, String mobileP, String personalemailP) {
        this.bdayP = bdayP;
        this.genderP = genderP;
        this.mobileP = mobileP;
        this.personalemailP = personalemailP;
    }

    public ProfileObject() {
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
