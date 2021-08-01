using System;
using Newtonsoft.Json;
using System.Linq;

namespace Students
{
    class Program
    {
        static void Main(string[] args)
        {
            var students = JsonParser.readList<Student>("students.json");
            var grades = JsonParser.readList<Grade>("grades.json");
            
            var gradeStudents = students.Select(s => new StudentWithGrade() 
            {
                StudentNumber = s.StudentNumber,
                FirstName = s.FirstName,
                LastName = s.LastName,
                Average = grades.Where(g => g.StudentNumber == s.StudentNumber).Average(g => g.Score)
            }).OrderByDescending(s => s.Average).Take(3).ToList();

            foreach (var student in gradeStudents)
            {
                Console.WriteLine(student);
            }
        }
    }
    public class Student
    {
        public int StudentNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
    }
    public class StudentWithGrade : Student
    {
        public decimal Average { get; set; }
        public override string ToString()
        {
            return $"{StudentNumber}, {FirstName} {LastName}, Average: {Average}";
        }
    }
    public class Grade 
    {
        public int StudentNumber { get; set; }
        public string Lesson { get; set; }
        public decimal Score { get; set; }
    }
}
