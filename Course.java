public class Course{

   private String courseCode;
   private String weeklyTimeSlots;
   private String finalExamDate;
   private double tuition;
   private String [] traineeNames;
   public static int totalCourses = 0;
   public int numTrainees;

   public Course(){
      courseCode = "";
      weeklyTimeSlots = "";
      finalExamDate = "";
      tuition = 0;
      numTrainees = 0;
      totalCourses++;
      traineeNames = new String [3];
   }

   public Course(String theCourseCode, String CTimeSlots, String CFinalExamDate, double CTuition){
   
      courseCode = theCourseCode;
      weeklyTimeSlots = CTimeSlots;
      finalExamDate = CFinalExamDate;
      tuition = CTuition;
      totalCourses++;
      numTrainees=0;
      traineeNames = new String [3];
   
   }
   
   
   public String getCourseCode(){
      return courseCode;}
      
   public String getWeeklyTimeSlots(){ 
      return weeklyTimeSlots;}
      
   public String getFinalExamDate(){ 
      return finalExamDate;}
      
   public double getTuition(){ 
      return tuition;}
      

   public String [] getTraineeNames(){ 
      return traineeNames;}

      
   public void setCourseCode(String courseCode){this.courseCode = courseCode; }
   public void setWeeklyTimeSlots(String weeklyTimeSlots){this.weeklyTimeSlots = weeklyTimeSlots; }
   public void setFinalExamDate(String finalExamDate){this.finalExamDate = finalExamDate; }
   public void setTuition(double tuition){this.tuition = tuition; }
   
   public void setTraineeNames(String [] traineeNames){
      for(int i=0; i<traineeNames.length; i++)
         this.traineeNames[i] = traineeNames[i];
    
   }

      
      
   public void DisplayCourseInfo(){
   
   
      System.out.println("\n***** Course Information *****");
      System.out.println("Customer Code: " + courseCode);
      System.out.println("Course Availability:" + (numTrainees<3 ? " Availabile": " Not Availabile") ); 
      System.out.println("Tuition: " + tuition );
      System.out.println("weeklyTimeSlots: ");
      System.out.print("Day: " + weeklyTimeSlots.substring(0,weeklyTimeSlots.indexOf('-')));//000-00-00
      System.out.println("\t\tTime: " + weeklyTimeSlots.substring(weeklyTimeSlots.indexOf('-')+1));
      System.out.println("FinalExamDate: "); //00/00/00
      System.out.print("Day: " + finalExamDate.substring(0,finalExamDate.indexOf('/')));
      System.out.print("\t\tMonth: " + finalExamDate.substring(finalExamDate.indexOf('/')+1,finalExamDate.lastIndexOf('/')));
      System.out.println("\t\tYear: " + finalExamDate.substring(finalExamDate.lastIndexOf('/')+1));
       
   
   }



}