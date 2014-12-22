using UnityEngine;
using System.Collections;

public class TrackPlayer : MonoBehaviour {
	public GameObject player;
	public Vector3 offset;

	void Update () {
		transform.position = player.transform.position + offset;
	}
}
