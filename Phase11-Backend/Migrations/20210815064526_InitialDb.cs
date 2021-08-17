using Microsoft.EntityFrameworkCore.Migrations;

namespace Phase11_Backend.Migrations
{
    public partial class InitialDb : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Docs",
                columns: table => new
                {
                    DocID = table.Column<string>(type: "nvarchar(5)", maxLength: 5, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Docs", x => x.DocID);
                });

            migrationBuilder.CreateTable(
                name: "Words",
                columns: table => new
                {
                    WordID = table.Column<int>(type: "int", maxLength: 5, nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Text = table.Column<string>(type: "nvarchar(max)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Words", x => x.WordID);
                });

            migrationBuilder.CreateTable(
                name: "DocWordsAssigns",
                columns: table => new
                {
                    DocWordID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    WordID = table.Column<int>(type: "int", nullable: false),
                    DocID = table.Column<string>(type: "nvarchar(5)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DocWordsAssigns", x => x.DocWordID);
                    table.ForeignKey(
                        name: "FK_DocWordsAssigns_Docs_DocID",
                        column: x => x.DocID,
                        principalTable: "Docs",
                        principalColumn: "DocID",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_DocWordsAssigns_Words_WordID",
                        column: x => x.WordID,
                        principalTable: "Words",
                        principalColumn: "WordID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_DocWordsAssigns_DocID",
                table: "DocWordsAssigns",
                column: "DocID");

            migrationBuilder.CreateIndex(
                name: "IX_DocWordsAssigns_WordID",
                table: "DocWordsAssigns",
                column: "WordID");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "DocWordsAssigns");

            migrationBuilder.DropTable(
                name: "Docs");

            migrationBuilder.DropTable(
                name: "Words");
        }
    }
}
