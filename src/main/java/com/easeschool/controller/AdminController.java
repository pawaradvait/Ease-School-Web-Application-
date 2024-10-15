package com.easeschool.controller;

import com.easeschool.model.Classes;
import com.easeschool.model.Courses;
import com.easeschool.model.Person;
import com.easeschool.repo.ClassesRepo;
import com.easeschool.repo.CoursesRepo;
import com.easeschool.repo.PersonRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ClassesRepo classesRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private CoursesRepo coursesRepo;


    @GetMapping("/displayClasses")
    public String getClasses(Model model) {

        List<Classes> classes = classesRepo.findAll();
         model.addAttribute("easyClasses", classes);
        model.addAttribute("easyClass", new Classes());
        return "classes.html";
    }

    @PostMapping("/addNewClass")
    public String addClasses( @Valid @ModelAttribute(name = "easyClass") Classes classes , Errors errors) {
        if (errors.hasErrors()) {
            return "classes.html";
        }
        classesRepo.save(classes);
        return "redirect:/admin/displayClasses";
    }

  //  /admin/displayStudents(classId=${easyClass.classId}
    @GetMapping("/displayStudents")
    public String getStudentsByClass(Model model,
                                     @RequestParam(required = true,value = "classId") int classId,
                                     HttpSession session,
                                      @RequestParam(required = false,value = "error") String error
                                     ) {

 Optional<Classes> classes = classesRepo.findById(classId);
 if(classes.isPresent()) {
     session.setAttribute("classes", classes.get());
 }

 model.addAttribute("easyClass", classes.get());
if(error !=null){
    model.addAttribute("errorMessage", "Invalid Email Address");
}
  model.addAttribute("person",new Person());
  return "student.html";




    }

    @PostMapping("/addStudent")
    public String addStudentinClass( @ModelAttribute(name = "person") Person person  ,HttpSession session    ) {
  Classes classes1 = (Classes) session.getAttribute("classes");
      Person person1 = personRepo.findByEmail(person.getEmail());
      if(person1 == null) {
          System.out.println("entered");
          return "redirect:/admin/displayStudents?classId="+classes1.getClassId()+"&error=true";

      }
   person1.setEaseClass(classes1);
   personRepo.save(person1);
//   classes1.getPersons().add(person1);
//   classesRepo.save(classes1);

 return "redirect:/admin/displayStudents?classId="+classes1.getClassId();
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent( HttpSession session,@RequestParam(value = "personId") int personId) {

        Optional<Person> person =personRepo.findById(personId);
        if(person.isPresent()) {
            person.get().setEaseClass(null);
        }
        personRepo.save(person.get());
        Classes classes = (Classes)session.getAttribute("classes");
         classes.getPersons().remove(person.get());
         classesRepo.save(classes);
         return "redirect:/admin/displayStudents?classId="+classes.getClassId();

    }

    @GetMapping("/deleteClass")
    public String deleteClass( HttpSession session,@RequestParam(value = "id") int classId) {

        Optional<Classes> classToDelete =  classesRepo.findById(classId);
         if(classToDelete.isPresent()) {
            for(Person person : classToDelete.get().getPersons()){
                person.setEaseClass(null);
                personRepo.save(person);
            }
            classesRepo.deleteById(classId);
         }
         return "redirect:/admin/displayClasses";


    }

    @GetMapping("/displayCourses")
    public String displayCourses(Model model) {
       List<Courses> courses =  coursesRepo.findAll();
       model.addAttribute("course" , new Courses());
        model.addAttribute("courses", courses);
      return "courses_secure.html";
    }

    @PostMapping("/addNewCourse")
    public String addNewCourse( @Valid @ModelAttribute(name = "course") Courses courses , Errors errors) {
        if(errors.hasErrors()) {
            return "courses_secure.html";
        }
        coursesRepo.save(courses);
        return "redirect:/admin/displayCourses";

    }

    @GetMapping("/deleteCourse")
    public String deleteCourse( @RequestParam(value = "id") int courseId) {
        Optional<Courses> course = coursesRepo.findById(courseId);
        if(course.isPresent()) {

            for(Person person : course.get().getPersons()){
                person.getCourses().remove(course.get());
                personRepo.save(person);
            }

              coursesRepo.delete(course.get());

        }
        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/viewStudents")
    public String viewStudents(Model model , @RequestParam(value = "id") int id,
                               HttpSession session
                               ) {

        Optional<Courses> course = coursesRepo.findById(id);
        session.setAttribute("course", course.get());
        if(course.isPresent()) {
            model.addAttribute("person" , new Person());

         model.addAttribute("courses" , course.get());
        }


        return "course_student.html";

    }

   @PostMapping("/addStudentToCourse")
   public String addStudentToCourse(HttpSession session , Model model ,  @ModelAttribute(name = "person") Person person  ) {
        Person person1 = personRepo.findByEmail(person.getEmail());
        if(person1 == null) {
                   model.addAttribute("errorMessage", "Invalid Email Address");
         return "courses_secure.html";
        }else{
            System.out.println("entered");
            Courses course=(Courses)session.getAttribute("course");
           person1.getCourses().add(course);


            personRepo.save(person1);
         return "redirect:/admin/viewStudents?id="+course.getCourseId();
        }
   }

   @GetMapping("/deleteStudentFromCourse")
    public String deleteStudentFromCourse(HttpSession session , @RequestParam(value = "personId") int personId) {

       Courses course = (Courses)session.getAttribute("course");
        Optional<Person> person = personRepo.findById(personId);
        //best practise to clear data from both side instead of owing side person
       course.getPersons().remove(person.get());
       coursesRepo.save(course) ;
       person.get().getCourses().remove(course);
       personRepo.save(person.get());
        return "redirect:/admin/viewStudents?id="+course.getCourseId();

   }
}
