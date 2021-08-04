namespace Students.Models
{
    public class StudentWithGrade : Student
    {
        public decimal Average { get; set; }
        public override string ToString()
        {
            return $"{StudentNumber}, {FirstName} {LastName}, Average: {Average}";
        }
    }
}