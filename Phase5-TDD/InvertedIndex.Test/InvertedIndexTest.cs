
using Xunit;
using InvertedIndex.Search;
using InvertedIndex.FileHandler;
using System.IO;
using System.Collections.Generic;

namespace InvertedIndex.Test
{
    public class InvertedIndexTest
    {
        private IList<Doc> testDocs;
        public InvertedIndexTest()
        {
            IDirectoryReader reader = new DirectoryReader();
            FileInfo[] testFiles = reader.getFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            FileReader fileReader = new FileReader();
            this.testDocs = fileReader.getFilesDocs(testFiles);
        }

        [Fact]
        public void invertedIndexGetTest() {
            var index = new InvertedIndex.Search.InvertedIndex(testDocs, new Mapper());
            var result = index.get("have");
            Assert.Equal(5, result.Count);
        }
    }
}