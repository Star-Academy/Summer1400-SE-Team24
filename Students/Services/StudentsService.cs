using System.Collections.Generic;
using Students.Models;
using System.Linq;

namespace Students.Services
{
    public class StudentsService
    {
        private readonly IList<Student> _students;

        public StudentsService(IList<Student> students, IList<Grade> grades)
        {
            _students = students.Select(s => 
            {
                s.Average = grades.Where(g => g.StudentNumber == s.StudentNumber).Average(g => g.Score);
                return s;
            }).ToList();
        }
        public IList<Student> GetBestAverages(int count)
        {
            return _students.OrderByDescending(s => s.Average).Take(count).ToList();
        }
    }
}