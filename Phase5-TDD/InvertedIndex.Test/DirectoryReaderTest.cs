using System.IO;
using System.Linq;
using Xunit;

using InvertedIndex.FileHandler;

namespace InvertedIndex.Test
{
    public class DirectoryReaderTest
    {
        private const int FILES_COUNT = 3;
        private readonly string[] TEST_FILE_NAMES = new string[] {"57110","59483","59519"};
        private readonly string TEST_DOCS_PATH = Path.GetFullPath("../../../docs/");
        private FileInfo[] _files;

        public DirectoryReaderTest()
        {
            var directoryReader = new DirectoryReader();
            _files = directoryReader.GetFiles(TEST_DOCS_PATH).OrderBy(f => f.Name).ToArray();
        }

        [Fact]
        public void ReadFilesCountTest()
        {
            Assert.Equal(FILES_COUNT, _files.Length);
        }

        [Fact]
        public void ReadFilesNamesTest()
        {
            for (int i = 0; i < TEST_FILE_NAMES.Length; i++)
            {
                Assert.Equal(TEST_FILE_NAMES[i], _files[i].Name);
            }
        }
    }
}