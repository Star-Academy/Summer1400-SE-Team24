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
            IDictionary<int, IList<decimal>> gradesDic = new Dictionary<int, IList<decimal>>(); 
            grades.ToList().ForEach(g => 
            {
                if(!gradesDic.ContainsKey(g.StudentNumber))
                {
                    gradesDic[g.StudentNumber] = new List<decimal>();
                }
                gradesDic[g.StudentNumber].Add(g.Score);
            });

            _students = new List<Student>();
            students.ToList().ForEach(s => {
                s.Average = gradesDic[s.StudentNumber].Average();
                _students.Add(s);
            });
        }
        public IList<Student> GetBestAverages(int count)
        {
            return _students.OrderByDescending(s => s.Average).Take(count).ToList();
        }
    }
}