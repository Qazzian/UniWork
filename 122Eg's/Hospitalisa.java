


/**
  * Problem:
  * Develop aclass model of the following
  *
  * Nurse                   Casualty            Ward
  * Midwife                 Out Patient         Theatre
  * Surgeon                 In Patient          Waiting Room
  * Doctor                  Orthopaedic         Reception
  * Porter                  Medical             Beds
  * Manager                 Surgical
  * Consultant
  * Gynaecologist
  * Radiographer
  */

 /*
  * Suggested solution - each class will be in a different file
  */

  public class abstract Person

  public class Staff extends Person

  public class AdminStaff extends Staff

  public class MedicalStaff extends Staff

  public class Nurse extends MedicalStaff

  public class MidWife extends Nurse

  public class Doctor extends MedicalStaff

  public class Surgeon extends Doctor

  public class Consultant extends Surgeon

  public class Gynaecologist extends Consultant

  public class Casualty extends Person

  public class InPatient extends Casualty

  public class OutPatient extends Casualty
