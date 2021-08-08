using System.Collections.Generic;
using InvertedIndex.FileHandler;
using Xunit;
using System.IO;
using System.Linq;

namespace InvertedIndex.Test
{
    public class FileHandlerTest
    {
        private FileInfo[] testFiles;
        private IList<Doc> testDocs;

        public FileHandlerTest(){
            IDirectoryReader reader = new DirectoryReader();
            testFiles = reader.GetFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            FileReader fileReader = new FileReader();
            this.testDocs = fileReader.getFilesDocs(testFiles);
        }

        [Fact]
        public void fileReadingTest() {
            IDirectoryReader reader = new DirectoryReader();
            var files = reader.GetFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            Assert.Equal(8, files.Length);
        }

        [Fact]
        public void docEqualsTest() {
            string sameName = "sameName";
            var content = new List<string>();
            Doc doc1 = new Doc(sameName, content);
            Doc doc2 = new Doc(sameName, content);

            Assert.Equal(doc1, doc2);
        }

        [Fact]
        public void getFilesDocsTest() {
            Assert.Equal(testFiles.Length, testDocs.Count);
            Assert.Equal(testDocs[0].getName(), testFiles[0].Name);
            Assert.Equal(8, testDocs[0].getWords().Count);
        }
    }
}