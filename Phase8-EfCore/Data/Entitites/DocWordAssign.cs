using System.Data;
using System.Linq;
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace InvertedIndex.Data.Entities
{
    public class DocWordAssign
    {
        [Key]
        public int DocWordID { get; set; }

        public int WordID { get; set; }
        public Word Word { get; set; }

        public string DocID { get; set; }
        public Doc Doc { get; set; }
    }
}