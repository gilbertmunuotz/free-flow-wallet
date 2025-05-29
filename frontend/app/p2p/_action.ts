/* eslint-disable @typescript-eslint/no-explicit-any */
'use server'

import { auth } from "@/auth";
import { SERVER_URI } from "@/constants/constant"
import axios from "axios"

export async function PostP2P(formData: FormData): Promise<void> {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    const receiverEmail = formData.get('receiverEmail') as string
    const amount = formData.get('amount') as string
    const pin = formData.get('pin') as string

    try {
        const response = await axios.post(`${SERVER_URI}/api/v1/transactions/p2p`, {
            receiverEmail,
            amount,
            pin
        }, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the balance data
    } catch (error: any) {
        console.error("Failed to Perform Transaction:", error);
        throw new Error("Failed to Perform Transaction");
    }
}

export async function GetP2P() {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    try {
        const response = await axios.get(`${SERVER_URI}/api/v1/transactions/p2p/history`, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the transaction data

    } catch (error: any) {
        console.error("Failed to fetch Transactions:", error.message);
        throw new Error("Could not fetch Transactions");
    }
}

export async function DeleteP2P(formData: FormData) {
    const session = await auth(); // get the user session
    const id = formData.get("id") as string;

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    try {
        const response = await axios.delete(`${SERVER_URI}/api/v1/transactions/p2p/${id}`, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        console.log("Status", response.status);

    } catch (error: any) {
        console.error("Failed to Delete Transaction:", error.message);
        throw new Error("Could not Delete Transaction");
    }
}