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
                _docManager.AddDoc(file.Name, ReadAllText(file));
            }
        }

        public string ReadAllText(FileInfo file)
        {
            if(!file.Exists){
                throw new FileNotFoundException();
            }
            return File.ReadAllText(file.FullName);
        }
    }
}