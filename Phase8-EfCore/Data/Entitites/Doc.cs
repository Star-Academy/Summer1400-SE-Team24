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
        public string Text { get; set; }
        
        [NotMapped]
        private IList<string> _words;
        public IList<string> GetWords()
        {
            if(_words == null)
            {
                _words = Regex.Replace(Text.ToLower(), "[^a-zA-Z0-9]", " ")
                    .Split(' ').Select(word => word.Trim())
                    .Where(word => !string.IsNullOrEmpty(word)).ToList<string>();
            }
            return _words;
        }

        public override string ToString()
        {
            return ID;
        }
    }
}