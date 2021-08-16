using System.Collections;
using System.Linq;
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace InvertedIndex.Data.Entities
{
    public class Word
    {
        [Key]
        [MaxLength(5)]
        public int WordID { get; set; }
        public string Text { get; set; }
        
        public IList<DocWordAssign> DocWordAssigns { get; set; }
    }
}