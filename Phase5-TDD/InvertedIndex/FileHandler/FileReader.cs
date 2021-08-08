using System.IO;
using System.Collections.Generic;
using System;
using System.Text.RegularExpressions;
using System.Linq;
namespace InvertedIndex.FileHandler
{
    public class FileReader
    {
        public IList<Doc> getFilesDocs(FileInfo[] files)
        {
            var docs = new List<Doc>();
        foreach (var file in files) {
            docs.Add(new Doc(file.Name, getFileWords(file)));
        }
        return docs;
        }

        public IList<string> getFileWords(FileInfo file)
        {

            if(!file.Exists){
                throw new FileNotFoundException();
            }
            var words = new List<string>();
            using (StreamReader reader = file.OpenText()){
                string line = null;
                while((line = reader.ReadLine()) != null){
                    line = line.ToLower();
                    List<string> lineWords = Regex.Replace(line, "[^a-zA-Z0-9]", " ")
                    .Split(' ').Select(word => word.Trim()).Where(word => !string.IsNullOrEmpty(word)).ToList<string>();
                    words.AddRange(lineWords);
                }

            }
            return words;
        }
    }
}