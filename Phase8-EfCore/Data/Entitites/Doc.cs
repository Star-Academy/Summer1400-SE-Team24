using System.Linq;
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace InvertedIndex.Data.Entities
{
    public class Doc
    {
        [Key]
        [MaxLength(5)]
        public string ID { get; set; }
        public string Text 
        {
            get { return Text; }
            set
            {
                Text = value;
                _words = Regex.Replace(value.ToLower(), "[^a-zA-Z0-9]", " ")
                    .Split(' ').Select(word => word.Trim())
                    .Where(word => !string.IsNullOrEmpty(word)).ToList<string>();
            }
        }
        [NotMapped]
        private IList<string> _words;
        public IList<string> GetWords()
        {
            return _words;
        }

        public override string ToString()
        {
            return ID;
        }
    }
}