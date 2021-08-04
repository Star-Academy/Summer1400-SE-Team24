using System;
using Students.Models;

namespace Students
{
    class Program
    {
        static void Main(string[] args)
        {
            var students = JsonParser.ReadList<Student>("students.json");
            var grades = JsonParser.ReadList<Grade>("grades.json");

            var service = new Students.Sevices.Students(students, grades);
            var gradeStudents = service.GetBestAverages(3);

            foreach (var student in gradeStudents)
            {
                Console.WriteLine(student);
            }
        }
    }
}
