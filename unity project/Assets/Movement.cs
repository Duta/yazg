using UnityEngine;
using System.Collections;

public class Movement : MonoBehaviour {
	public float movementSpeed = 10f;
	public float rotationSpeed = 10f;
	public CharacterController cc;
	private Vector3 goal;

	void Update() {
		if(Input.GetMouseButton(0)) {
			Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
			RaycastHit hit;
			if(Physics.Raycast(ray, out hit)) {
				goal = hit.point;
			}
		}
		if(Vector3.Distance(transform.position, goal) > 1) {
			Quaternion rotation = Quaternion.LookRotation(goal - transform.position);
			rotation.x = 0f;
			rotation.z = 0f;
			transform.rotation = Quaternion.Slerp(transform.rotation, rotation, Time.deltaTime * rotationSpeed);
			cc.SimpleMove(movementSpeed * transform.forward);
		}
	}
}
