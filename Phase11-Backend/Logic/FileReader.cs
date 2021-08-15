using System.IO;
using System.Linq;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using InvertedIndex.Data.Entities;
using InvertedIndex.Data.Services;

namespace InvertedIndex.Logic
{
    public class FileReader
    {
        private readonly IDocRepository _docManager;
        
        public FileReader(IDocRepository docManager)
        {
            _docManager = docManager;
        }

        public void AddFilesToDataBase(string path)
        {
            var directory = new DirectoryInfo(path);

            foreach (var file in directory.GetFiles())
            {
                System.Console.WriteLine(file.Name);
                _docManager.AddDoc(file.Name);
                _docManager.Save();
                var words = Split(ReadAllText(file));
                foreach (var word in words)
                {
                    _docManager.AddWord(word);
                    _docManager.Save();
                }
                _docManager.AssignDocToWords(file.Name, words);
                _docManager.Save();
            }
        }

        public string ReadAllText(FileInfo file)
        {
            if(!file.Exists){
                throw new FileNotFoundException();
            }
            return File.ReadAllText(file.FullName);
        }

        public IList<string> Split(string text)
        {
            return Regex.Replace(text, "[^a-zA-Z0-9]", " ")
                .Split(' ').Select(word => word.Trim())
                .Where(word => !string.IsNullOrEmpty(word)).ToList<string>();
        }
    }
}