import org.junit.Assert.*
import ru.netology.Comments
import ru.netology.NoteService
import ru.netology.Notes
import kotlin.test.Test

class NoteServiceTest {

    @Test
    fun addNote() {
        val service = NoteService()
        val result = service.addNote(Notes("Новая заметка", "Текст для новой заметки"))
        assertEquals(result, 1)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        val result = service.createComment(Comments(), 1)
        assertEquals(result, 1)
    }

    @Test
    fun deleteNote() {
        val service = NoteService()
        service.addNote(Notes("Новая заметка", "Текст для новой заметки"))
        val result = service.deleteNote(1)
        assertTrue(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteNoteIllegalArgumentException() {
        val service = NoteService()
        service.deleteNote(1)
    }

    @Test
    fun deleteComments() {
        val service = NoteService()
        service.createComment(Comments(), 1)
        val result = service.deleteComments(1)
        assertTrue(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteCommentsIllegalArgumentException() {
        val service = NoteService()
        service.deleteComments(1)
    }

    @Test
    fun editNote() {
        val service = NoteService()
        service.addNote(Notes("Новая заметка", "Текст для новой заметки"))
        assertTrue(service.editNote(1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun editNoteIllegalArgumentException() {
        val service = NoteService()
        service.editNote(1)
    }

    @Test
    fun editComment() {
        val service = NoteService()
        service.createComment(Comments(), 1)
        assertTrue(service.editComment(1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun editCommentIllegalArgumentException() {
        val service = NoteService()
        service.editComment(1)
    }

    @Test
    fun getNotes() {
        val service = NoteService()
        service.addNote(Notes("Новая заметка", "Текст для новой заметки"))
        val note = Notes("Новая заметка", "Текст для новой заметки", userId = 1, noteId = 1)
        assertEquals(service.getNotes(1), note)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getNotesIllegalArgumentException() {
        val service = NoteService()
        service.getNotes(1)
    }

    @Test
    fun getNote() {
        val service = NoteService()
        service.addNote(Notes("Новая заметка", "Текст для новой заметки"))
        val note = Notes("Новая заметка", "Текст для новой заметки", userId = 1, noteId = 1)
        assertEquals(service.getNote(1), note)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getNoteIllegalArgumentException() {
        val service = NoteService()
        service.getNote(1)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        service.createComment(Comments(), 1)
        val comment = Comments(1, "Новый комментарий", 1)
        assertEquals(service.getComments(1), comment)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getCommentsIllegalArgumentException() {
        val service = NoteService()
        service.getComments(1)
    }

    @Test
    fun restoreComments() {
        val service = NoteService()
        service.createComment(Comments(), 1)
        service.deleteComments(1)
        val result = service.restoreComments(1)
        assertTrue(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun restoreCommentsIllegalArgumentException() {
        val service = NoteService()
        service.createComment(Comments(), 1)
        service.deleteComments(1)
        val result = service.restoreComments(2)
        assertTrue(result)
    }
}