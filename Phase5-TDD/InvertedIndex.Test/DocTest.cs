using System;
using System.Collections.Generic;
using Xunit;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Test 
{
    public class DocTest : IDisposable
    {
        private const string SAMPLE_NAME = "docName";
        private readonly IList<string> SAMPLE_WORDS = new List<string>() {"word1","word2","word3"};

        private Doc _testDoc;

        public DocTest()
        {
            _testDoc = new Doc(SAMPLE_NAME, SAMPLE_WORDS);
        }

        [Fact]
        public void GetNameTest()
        {
            var name = _testDoc.GetName();
            Assert.Equal(SAMPLE_NAME, name);
        }

        [Fact]
        public void GetWordsTest()
        {
            var words = _testDoc.GetWords();
            Assert.Equal(SAMPLE_WORDS, words);
        }

        [Fact]
        public void EqualsTest()
        {
            Assert.Equal(new Doc(SAMPLE_NAME, SAMPLE_WORDS), _testDoc);
        }

        [Fact]
        public void HashCodeTest()
        {
            Assert.Equal(SAMPLE_NAME.GetHashCode(), _testDoc.GetHashCode());
        }

        public void Dispose()
        {
            GC.Collect();
        }
    }
}