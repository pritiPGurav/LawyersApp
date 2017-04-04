
package com.rasp.lawyersapp.pojos.CategoryList.LawyerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rasp.lawyersapp.pojos.CategoryList.CategoryList;

public class LawyerList {

    @SerializedName("dateOfBirth")
    @Expose
    private Integer dateOfBirth;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("lawyer_id")
    @Expose
    private Integer lawyerId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("portrait_url")
    @Expose
    private String portraitUrl;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile_number")
    @Expose
    private Integer mobileNumber;
    @SerializedName("land_line_number")
    @Expose
    private Integer landLineNumber;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("qualifications")
    @Expose
    private String qualifications;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("experience")
    @Expose
    private Integer experience;
    @SerializedName("about_lawyer")
    @Expose
    private String aboutLawyer;
    @SerializedName("category_id")
    @Expose
    private CategoryList categoryList;
    @SerializedName("rating")
    @Expose
    private Double rating;

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(Integer lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getLandLineNumber() {
        return landLineNumber;
    }

    public void setLandLineNumber(Integer landLineNumber) {
        this.landLineNumber = landLineNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getAboutLawyer() {
        return aboutLawyer;
    }

    public void setAboutLawyer(String aboutLawyer) {
        this.aboutLawyer = aboutLawyer;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
