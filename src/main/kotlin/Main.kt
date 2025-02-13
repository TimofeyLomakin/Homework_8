package ru.netology


data class Notes(
    val title: String = "",
    val text: String = "",
    val privacyView: PrivacyViewType = PrivacyViewType.ALL,
    val privacyComment: PrivacyViewType = PrivacyViewType.ALL,
    val noteId: Int = 0,
    val userId: Int = 1
)

data class Comments(
    val noteId: Int = 0,
    val message: String = "",
    val commentId: Int = 0
)


class NoteService {
    val notes = mutableListOf<Notes>()
    val comments = mutableListOf<Comments>()
    val deleteComments = mutableListOf<Comments>()
    var noteId = 1
    var commentId = 1

    fun addNote(note: Notes): Int {
        val nextNote = Notes(
            title = note.title,
            text = note.text,
            privacyView = note.privacyView,
            privacyComment = note.privacyComment,
            noteId = noteId++,
            userId = note.userId
        )

        notes.add(nextNote)
        return nextNote.noteId
    }

    fun createComment(comment: Comments, noteId: Int): Int {
        val createComment = Comments(
            noteId = noteId,
            message = "Новый комментарий",
            commentId = commentId++
        )
        comments.add(createComment)
        return createComment.commentId
    }

    fun deleteNote(noteId: Int): Boolean {
        val noteToDelete = notes.find { it.noteId == noteId } ?: throw IllegalArgumentException("Заметка не найдена")
        if (comments.find { it.noteId == noteId } != null) {
            comments.remove(comments.find { it.noteId == noteId })
        }
        notes.remove(noteToDelete)
        return true
    }

    fun deleteComments(commentId: Int): Boolean {
        val commentToDelete =
            comments.find { it.commentId == commentId } ?: throw IllegalArgumentException("Комментарий не найден")
        deleteComments.add(commentToDelete)
        comments.remove(commentToDelete)
        return true
    }

    fun editNote(noteId: Int): Boolean {
        val noteToEdit = notes.find { it.noteId == noteId } ?: throw IllegalArgumentException("Заметка не найдена")
        val updateNote = noteToEdit.copy(
            title = "Измененная заметка",
            text = "Текст для измененной заметки"
        )
        notes[notes.indexOf(noteToEdit)] = updateNote
        return true
    }

    fun editComment(commentId: Int): Boolean {
        val commentToEdit =
            comments.find { it.commentId == commentId } ?: throw IllegalArgumentException("Комментарий не найден")
        val updateComment = commentToEdit.copy(message = "Обновленный комментарий")
        comments[comments.indexOf(commentToEdit)] = updateComment
        return true
    }

    fun getNotes(userId: Int): Notes {
        val notesToGet = notes.find { it.userId == userId } ?: throw IllegalArgumentException("Пользователь не найден")
        return notes[notes.indexOf(notesToGet)]
    }

    fun getNote(noteId: Int): Notes {
        return notes.find { it.noteId == noteId } ?: throw IllegalArgumentException("Пользователь не найден")
    }

    fun getComments(noteId: Int): Comments {
        return comments.find { it.noteId == noteId } ?: throw IllegalArgumentException("Комментарий не найден")
    }

    fun restoreComments(noteId: Int): Boolean {
        val deleteComments =
            deleteComments.find { it.noteId == noteId } ?: throw IllegalArgumentException("Комментарий не найден")
        comments.add(deleteComments)
        return true
    }


}


fun main() {
}