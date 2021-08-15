using System.Security.Cryptography.X509Certificates;
using System;
using Students.Models;
using Students.Services;

namespace Students
{
    class Program
    {
        static void Main(string[] args)
        {
            var students = JsonParser.ReadList<Student>("students.json");
            var grades = JsonParser.ReadList<Grade>("grades.json");

            var service = new StudentsService(students, grades);
            var gradeStudents = service.GetBestAverages(3);

            foreach (var student in gradeStudents)
            {
                Console.WriteLine(student);
            }
        }
    }
}
