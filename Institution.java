import java.util.*;
public class Institution{

   private static Course Courses[] = new Course[20];
   private static Scanner input = new Scanner(System.in);
   
   public static void main (String [] args){
      
      String courseCode,traineeName;
      int choice, i=0;
      Course [] array;
      
      addCourse("IT01","135-11-12", "12/02/21",3000);
      addCourse("CS01","135-08-09", "13/02/21",2000);
      addCourse("IS01","135-09-10", "11/02/21",1000);
      addCourse("SW01","234-08-09", "14/02/21",2000);
      addCourse("IT20","135-12-01", "20/02/21",1000);
      
      registerCourse("IT01","Ali");
      registerCourse("IT01","Sara");
      registerCourse("CS01","Sara");
      registerCourse("IS01","Sara");
      registerCourse("IS01","Fay");
      registerCourse("SW01","Ali");
      
      
      System.out.println("**** Welcome to Institute Course Registration System ****");
   
      do{
      
         System.out.println("\n---------------------------------------");
      
         System.out.println("\nChoose one of these actions:");
         System.out.println("(1) Add a new course\n(2) Register a course for a given trainee\n(3) Cancel course registration or a trainee, given the trainee name and course code\n(4) Find a course by course code\n(5) List all courses\n(6) List all registered courses for a given trainee\n(7) Compute and display total tuition for a given trainee\n(8) Display the total number of courses\n(9) Exit");
         choice = input.nextInt();
         input.nextLine();//clean
      
         switch(choice){
         
            case 1:
               System.out.println("Enter the course information:");
               
               System.out.print("Course Code in format LLDD, L: letter, Dd: igit: ");
               courseCode=input.next();
               
               System.out.print("weeklyTimeSlots in format D-S-E: ");
               String weeklyTime=input.next();
               
               System.out.print("finalExamDate in format : DD/MM/YY: ");
               String finalDate=input.next();
               
               System.out.print("Tuitions: ");
               double t=input.nextDouble();
               
               
               if(addCourse(courseCode,weeklyTime, finalDate, t))
                  System.out.println("The course is added successfully.");
               else
                  System.out.println("Course is not added");
            
               break;
         
            case 2:
            
               System.out.print("Please enter course code: ");
               courseCode=input.next();
             
               System.out.print("Please enter trainee name: ");
               traineeName=input.next();
               
            
               if(registerCourse(courseCode, traineeName))
                  System.out.println("Course registerrd successfully.");
               else
                  System.out.println("Course is not registered.");
               
               
               break;
            
            case 3:
            
               System.out.print("Enter Course Code: ");
               courseCode=input.next();
            
               System.out.print("Enter Trainee Name: ");
               traineeName=input.next();
            
               
               if(cancelCourseRegisteration(courseCode, traineeName))
                  System.out.println("Course registeration is canceled successfully.");
               else
                  System.out.println("Course cancelation was unsuccessful.");
                  
               break;
               
            case 4:
            
               System.out.print("Enter Course Code to find: ");
               courseCode=input.next();
               
               if(findCourse(courseCode) != -1)
                  Courses[findCourse(courseCode)].DisplayCourseInfo();
               else
                  System.out.println("This course cannot be found");
            
               break;
           
            case 5:
               System.out.println("All courses: ");
               printAll();
               
               break;
            
            case 6:
            
               System.out.print("Please enter trainee Name: ");
               traineeName=input.next();
            
               array = findRegisteredCourses(traineeName);
               
               i=0;
              
               while(i<array.length && array[i] != null)
                  System.out.println(array[i++].getCourseCode());
              
               if(i==0)
                  System.out.println("Entered trainee has no courses");
            
               break;
               
            case 7:
               double tuition=0;
               System.out.print("Please enter trainee Name: ");
               traineeName=input.next();
               
               array = findRegisteredCourses(traineeName);
               
               i=0;
               while(i<array.length && array[i] != null)
                  tuition+=array[i++].getTuition();
               
               if(i>0)
                  System.out.println("The total tuition is " + tuition);
               else
                  System.out.println("Entered trainee has no courses");
            
               break;
               
               
            case 8:
               System.out.println("Total number of courses: " + Course.totalCourses);
               break;
         
            case 9:
               System.out.println("Thank you.");
         }
      
      
      }while(choice != 9);
      
   
   
   }//end main

   public static boolean addCourse(String courseCode, String weeklyTimeSlot, String finalExamDate, double tuition){
   
      if(Course.totalCourses < 20 && findCourse(courseCode) == -1 ){
         Courses[Course.totalCourses]= new Course(courseCode, weeklyTimeSlot, finalExamDate, tuition);
         return true;}
            
      return false;
     
      
   }
   
   public static boolean registerCourse(String courseCode, String traineeName){
      
      int index = findCourse(courseCode), i=0;
      
      if(index != -1 && Courses[index].numTrainees < 3){
                  
         String finalExam = Courses[index].getFinalExamDate();
         String weeklySession = Courses[index].getWeeklyTimeSlots();
      
         int firstCurHour = Integer.parseInt(weeklySession.substring(weeklySession.indexOf('-')+1,weeklySession.lastIndexOf('-')));
      
         Course [] c = findRegisteredCourses(traineeName);
        
         if( c[c.length-1] != null ){
            return false;}
            
                            
         while(i<c.length && c[i] != null){
           
            int firstHour = Integer.parseInt(c[i].getWeeklyTimeSlots().substring(c[i].getWeeklyTimeSlots().indexOf('-')+1, c[i].getWeeklyTimeSlots().lastIndexOf('-')));
            
            if(finalExam.equals(c[i].getFinalExamDate()))
               return false;
         
            for(int j=0; j<3; j++)/* 3 since we are just checking the 3 days */
               if(c[i].getWeeklyTimeSlots().charAt(j)== weeklySession.charAt(j) && firstHour==firstCurHour){
                  return false;}
         
            i++;
            
         }//end while 
         
             
         String [] trainees = Courses[index].getTraineeNames();
         for(int j=0; j<trainees.length; j++)
            if(trainees[j] == null){
               trainees[j] = traineeName;
               Courses[index].setTraineeNames(trainees);
               Courses[index].numTrainees++;
               return true;
            }
         
      
      }//end if
      
      return false; /*If course dne or numberOfTrainees for this course reached max*/
   
   }
   
     
   public static boolean cancelCourseRegisteration(String courseCode, String traineeName){
   
      int target = findCourse(courseCode);
   
      if( target != -1){
         String [] name = Courses[target].getTraineeNames();
            
         for(int i=0; i<name.length; i++){
            if(traineeName.equalsIgnoreCase(name[i])){
               for(int j=i; j<name.length-1; j++)
                  name[j]=name[j+1];
                  
               name[name.length-1]=null;
               Courses[target].setTraineeNames(name);
               Courses[target].numTrainees--;
               return true;} 
         }
      }
    
      return false;
   }



   public static int findCourse(String courseCode){
      
      for(int i=0; i < Course.totalCourses; i++)
         if(courseCode.equalsIgnoreCase(Courses[i].getCourseCode()))
            return i;
      return -1;
      
   }
   
      
   public static Course [] findRegisteredCourses(String traineeName){
      int m = 0;
      Course c[] = new Course[3]; /*max number of courses for each trainee is 3*/
      
      for(int i=0; i<Course.totalCourses; i++){
         for(int j=0; j<Courses[i].numTrainees; j++ )
            if(traineeName.equalsIgnoreCase(Courses[i].getTraineeNames()[j]))
               c[m++]=Courses[i];
      }
      
      return c;
      
   }
   
   public static void printAll(){
      for(int i=0; i < Course.totalCourses; i++){
         Courses[i].DisplayCourseInfo();
      }
   }
   
     
      
}
      
      
   

   
   
   


