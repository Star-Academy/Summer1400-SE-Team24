using System;
using System.IO;
using System.Collections.Generic;
using Xunit;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Test
{
    public class FileReaderTest : IDisposable
    {
        private const string BASE_PATH = "InvertedIndex.Test/docs";
        private const string TEST_FILE_NAME = "57110";
        private const string TEST_FILE_PATH1 = BASE_PATH + TEST_FILE_NAME;
        private readonly IList<string> TEST_FILE_WORDS = new List<string>() {"i","have","a","42","yr","old","male","friend"};
        private FileReader _fileReader;

        public FileReaderTest()
        {
            _fileReader = new FileReader();
        }

        [Fact]
        public void FileWordsReadingTest()
        {
            var file = new FileInfo(TEST_FILE_PATH1);
            var words = _fileReader.GetFileWords(file);

            Assert.Equal(TEST_FILE_WORDS, words);
        }

        [Fact]
        public void FileConvertToDocTest()
        {
            var docs = _fileReader.getFilesDocs(new FileInfo[] {new FileInfo(TEST_FILE_PATH1)});

            Assert.Equal(new List<Doc>()
            {
                new Doc(TEST_FILE_NAME, TEST_FILE_WORDS)
            }, docs);
        }

        public void Dispose()
        {
            GC.Collect();
        }
    }
}