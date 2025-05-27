/* eslint-disable @typescript-eslint/no-explicit-any */
'use client'
import { DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { InputOTP, InputOTPGroup, InputOTPSlot } from "./ui/input-otp"
import { Button } from "./ui/button"
import { P2P } from "@/app/p2p/_action"
import { useState } from "react"
import { toast } from 'sonner'
import { useRouter } from "next/navigation";


export default function P2pModal() {

    const router = useRouter();

    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);

        const formData = new FormData(e.currentTarget);

        try {
            await P2P(formData)
            router.push("/home")
            toast.success("Transaction Sent Successfully")
        } catch (error: any) {
            toast.error(error.message || "Transaction failed")
        }
        finally {
            setLoading(false);
        }
    };

    return (
        <DialogHeader>
            <DialogTitle className="text-center">New P2P Transaction</DialogTitle>
            <form onSubmit={handleSubmit}>
                <div className="flex flex-col gap-6">
                    <div className="grid gap-3">
                        <Label htmlFor="email">Email</Label>
                        <Input
                            name="receiverEmail"
                            type="email"
                            placeholder="johndoe@example.com"
                            required
                        />
                    </div>
                    <div className="grid gap-3">
                        <Label htmlFor="amount">Amount</Label>
                        <Input
                            name="amount"
                            type="text"
                            placeholder="20"
                            required
                        />
                    </div>
                    <div className="grid">
                        <Label htmlFor="pin">4 Digit Pin for Your Wallet
                            <InputOTP maxLength={4} name="pin" required>
                                <InputOTPGroup>
                                    <InputOTPSlot index={0} />
                                    <InputOTPSlot index={1} />
                                    <InputOTPSlot index={2} />
                                    <InputOTPSlot index={3} />
                                </InputOTPGroup>
                            </InputOTP>
                        </Label>
                    </div>
                    <DialogFooter>
                        {loading ? (
                            <Button className="cursor-not-allowed" disabled>Save</Button>
                        ) : (
                            <Button type="submit">Save</Button>
                        )}
                    </DialogFooter>
                </div>
            </form>
        </DialogHeader>
    )
}
