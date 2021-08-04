using System.Collections.Generic;
using Students.Models;
using System.Linq;

namespace Students.Sevices
{
    public class Students
    {
        private IList<StudentWithGrade> _students;

        public Students(IList<Student> students, IList<Grade> grades)
        {
            _students = students.Select(s => new StudentWithGrade() 
            {
                StudentNumber = s.StudentNumber,
                FirstName = s.FirstName,
                LastName = s.LastName,
                Average = grades.Where(g => g.StudentNumber == s.StudentNumber).Average(g => g.Score)
            }).ToList();
        }
        public IList<StudentWithGrade> GetBestAverages(int count)
        {
            return _students.OrderByDescending(s => s.Average).Take(count).ToList();
        }
    }
}