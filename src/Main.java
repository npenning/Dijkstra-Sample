import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		int[][] edges;
		int[][] nodes;
		int n;
		int sourceID, destID;
		int currentNode;
		int nextCandidate = -1;
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter the input file path: ");
		String filepath = scan.nextLine();
		
		File inFile = new File(filepath);
		Scanner fileScan = new Scanner(inFile);
		
		
		n = fileScan.nextInt();
		edges = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				edges[i][j] = fileScan.nextInt();	
			}
		}
		fileScan.close();
		
		System.out.print("Please enter source: ");
		while(true){
			sourceID = (scan.next().toLowerCase().charAt(0)) - 97;
			
			if(sourceID < n && sourceID >= 0) {
				break;
			}
			else{
				System.out.print("Node does not exist. Pleae enter another: ");
			}
		}
		
		System.out.print("Please enter destination: ");
		while(true){
			destID = (scan.next().toLowerCase().charAt(0)) - 97;
			
			if(destID < n && destID >= 0) {
				break;
			}
			else{
				System.out.print("Node does not exist. Pleae enter another: ");
			}
		}
		scan.close();
		
		currentNode = sourceID;
		
		nodes = new int[n][3];
		for(int[] node: nodes) {
			node[0] = -1;
			node[1] = 0;
			node[2] = -1;
		}
		nodes[currentNode][0] = 0;
		
		while(true) {
			nodes[currentNode][1] = 1;
			if(currentNode == destID) {
				break;
			}
			
			for(int i = 0; i < n; i++) {
				if(nodes[i][1] == 0 && edges[currentNode][i] != -1) {
					if(nodes[i][0] == -1 || nodes[i][0] > nodes[currentNode][0] + edges[currentNode][i]) {
						nodes[i][0] = nodes[currentNode][0] + edges[currentNode][i];
						nodes[i][2] = currentNode;
					}
				}
			}
			for(int i = 0; i < nodes.length; i++) {
				if(nodes[i][0] != -1 && ((nodes[i][0] < nodes[currentNode][0] && nodes[i][1] == 0) || nodes[currentNode][1] == 1)) {
					currentNode = i;
				}
			}
			
			
			
		}
		
		StringBuilder out = new StringBuilder();
		while(currentNode != -1) {
			out.append((char)(currentNode + 97));
			out.append(nodes[currentNode][2]==-1?"":" >- ");
			currentNode = nodes[currentNode][2];
		}
		
		out.reverse();
		
		System.out.println("Routing Path:");
		System.out.println(out.toString());
		
		
		

	}

}
