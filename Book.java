/**
 */
package BookModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Book#getBookId <em>Book Id</em>}</li>
 * </ul>
 *
 * @see BookModel.BookModelPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends EObject {
	/**
	 * Returns the value of the '<em><b>Book Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Book Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Book Id</em>' attribute.
	 * @see #setBookId(int)
	 * @see BookModel.BookModelPackage#getBook_BookId()
	 * @model
	 * @generated
	 */
	int getBookId();

	/**
	 * Sets the value of the '{@link Book#getBookId <em>Book Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Book Id</em>' attribute.
	 * @see #getBookId()
	 * @generated
	 */
	void setBookId(int value);

} // Book
