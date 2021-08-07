namespace Students.Models
{
    public class Student
    {
        public int StudentNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public decimal Average { get; set; }
        public override string ToString()
        {
            return $"{StudentNumber}, {FirstName} {LastName}, Average: {Average}";
        }
    }
}