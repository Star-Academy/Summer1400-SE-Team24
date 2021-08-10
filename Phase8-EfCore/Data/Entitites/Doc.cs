using System.Data;
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
        public string DocID { get; set; }

        public int DocWordAssignID { get; set; }
        public DocWordAssign DocWordAssign { get; set; }
    }
}